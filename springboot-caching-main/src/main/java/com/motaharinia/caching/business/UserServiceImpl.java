package com.motaharinia.caching.business;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.motaharinia.caching.presentation.model.UserModel;

@Service
public class UserServiceImpl implements UserService {
	private HashMap<Integer, UserModel> userDatabaseMap = new HashMap<>();

	@CacheEvict(value = "userFindAll", allEntries = true)
	@Override
	public UserModel create(UserModel userModel) {
		Optional<Integer> pk = this.userDatabaseMap.keySet().stream().reduce(Integer::max);
		if (pk.isPresent()) {
			userModel.setId(pk.get() + 1);
		} else {
			userModel.setId(1);
		}
		this.userDatabaseMap.put(userModel.getId(), userModel);
		return this.userDatabaseMap.get(userModel.getId());
	}

	@Cacheable(value = "userFindOne", key = "#id", unless = "#result.followerCount < 12000")
	@Override
	public UserModel findOne(Integer id) {
		if (this.userDatabaseMap.containsKey(id)) {
			UserModel userModel = this.userDatabaseMap.get(id);
			return userModel;
		} else {
			return null;
		}
	}

	@Cacheable(value = "userFindAll")
	@Override
	public List<UserModel> findAll() {
		return this.userDatabaseMap.values().stream().collect(Collectors.toList());
	}

	@CachePut(value = "userFindOne", key = "#userModel.id")
	@CacheEvict(value = "userFindAll", allEntries = true)
	@Override
	public UserModel update(UserModel userModel) {
		if (this.userDatabaseMap.containsKey(userModel.getId())) {
			this.userDatabaseMap.remove(userModel.getId());
			this.userDatabaseMap.put(userModel.getId(), userModel);
			return this.userDatabaseMap.get(userModel.getId());
		} else {
			return null;
		}
	}

	@Caching(evict = { @CacheEvict(value = "userFindOne", key = "#id"),
			@CacheEvict(value = "userFindAll", allEntries = true) })
	@Override
	public UserModel delete(Integer id) {
		if (this.userDatabaseMap.containsKey(id)) {
			UserModel userModel = this.userDatabaseMap.get(id);
			this.userDatabaseMap.remove(id);
			return userModel;
		} else {
			return null;
		}
	}
}
