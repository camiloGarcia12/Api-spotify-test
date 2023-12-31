package com.jts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jts.entity.UserDetails;
import com.jts.entity.UserDetailsRepository;
import se.michaelthelin.spotify.model_objects.specification.User;
import java.util.Objects;

@Service
public class UserProfileService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public UserDetails insertOrUpdateUserDetails(User user, String accessToken, String refreshToken) {
		UserDetails userDetails = userDetailsRepository.findByRefId(user.getId());

		if (Objects.isNull(userDetails)) {
			userDetails = new UserDetails();

		}
		userDetails.setUserName(user.getDisplayName());
		userDetails.setEmailId(user.getEmail());
		userDetails.setAccessToken(accessToken);
		userDetails.setRefreshToken(refreshToken);
		userDetails.setRefId(user.getId());
		return userDetailsRepository.save(userDetails);

	}
}