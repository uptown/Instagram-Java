package com.sola.instagram.test;

import java.util.Random;

import com.sola.instagram.InstagramSession;
import com.sola.instagram.auth.AccessToken;
import com.sola.instagram.model.Media;
import com.sola.instagram.model.Relationship;
import com.sola.instagram.model.User;
import com.sola.instagram.model.VideoMedia;
import com.sola.instagram.util.PaginatedCollection;

import org.json.JSONException;
import org.junit.Test;

public class InstagramSessionTest {

	private InstagramSession getNewSession() {
		InstagramSession sess = new InstagramSession(new AccessToken(Constants.ACCESS_TOKEN));
		//sess.setHttpProxy("180.180.121.156", 8080);
		return sess;
	}

	@Test
	public void testGetUserById() throws Exception {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		getNewSession().getUserById(Constants.VALID_USER_ID);
	}

	@Test
	public void testGetRecentPublishedMedia() throws Exception {
		InstagramSession session = this.getNewSession();
		PaginatedCollection<Media> media = session.getRecentPublishedMedia(Constants.VALID_USER_ID);
		int i = 0;
		for(Media _media: media) {
			System.out.println(_media);
			if(i++ == 50) {
				break;
			}
		}		
	}

	@Test
	public void testGetFeed() throws Exception, JSONException {
		InstagramSession session = this.getNewSession();
		/*
		 * Test that no Exception is thrown for a valid page number
		 */
		PaginatedCollection<Media> media = session.getFeed();	
		int i = 0;
		for(Media _media: media) {
			System.out.println(_media);
			if(i++ == 50) {
				break;
			}
		}			
	}

	@Test
	public void testGetLikedMedia() throws Exception {
		InstagramSession session = this.getNewSession();
		session.getLikedMedia();
	}

	@Test
	public void testGetMedia() throws Exception {
		/*
		 * Test that no Exception is thrown for a valid media id
		 */
		VideoMedia media = (VideoMedia)getNewSession().getMedia(Constants.VALID_MEDIA_ID);
		return;
	}

	@Test
	public void testSearchMedia() throws Exception {
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().searchMedia(48.858844, 2.294351, null, null, null);
	}

	@Test
	public void testGetPopularMedia() throws Exception {
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().getPopularMedia();
	}

	@Test
	public void testSearchUsersByName() throws Exception {
		/*
		 * Test that no Exception is thrown
		 */
		System.out.print(getNewSession().searchUsersByName(Constants.VALID_USERNAME));
	}

	@Test
	public void testGetFollows() throws Exception {
		InstagramSession session = this.getNewSession();

		PaginatedCollection<User> follows = session.getFollows(Constants.VALID_USER_ID);
		for(User user: follows) {
			System.out.println(user);
		}
	}

	@Test
	public void testGetFollowers() throws Exception {
		InstagramSession session = this.getNewSession();
		PaginatedCollection<User> followers = session.getFollowers(Constants.VALID_USER_ID);
		for(User user: followers) {
			System.out.println(user);
		}		
	}

	@Test
	public void testGetFollowRequests() throws Exception {
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().getFollowRequests();
	}

	@Test
	public void testGetRelationshipWith() throws Exception {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		getNewSession().getRelationshipWith(Constants.VALID_USER_ID);
	}

	@Test
	public void testModifyRelationship() throws Exception {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		InstagramSession session = getNewSession();
		Relationship.Action action = null;
		if (session.getRelationshipWith(Constants.VALID_USER_ID)
				.getOutgoingStatus() == Relationship.OutgoingStatus.FOLLOWS)
			action = Relationship.Action.UNFOLLOW;
		else
			action = Relationship.Action.FOLLOW;
		//assertTrue(session.modifyRelationship(Constants.VALID_USER_ID, action));
	}

	@Test
	public void testPostComment() throws Exception {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		Random rand = new Random(19580427);
		InstagramSession session = getNewSession();
		Media media = session.getPopularMedia().get(rand.nextInt() % 10);
		//Comment comment = session.postComment(media.getId(), "nice pic");
	}

	@Test
	public void testRemoveComment() throws Exception,
			InterruptedException {
		Random rand = new Random(19580427);
		InstagramSession session = getNewSession();
		Media media = session.getPopularMedia().get(rand.nextInt() % 10);
		//Comment comment = session.postComment(media.getId(), "nice pic");
		//Thread.sleep(5);
		/*
		 * Test that no Exception is thrown for a valid comment id
		 */
		//assertTrue(session.removeComment(media.getId(), comment.getId()));
	}

	@Test
	public void testLikingAndUnlikingMedia() throws Exception {
		Random rand = new Random(19580427);
		InstagramSession session = getNewSession();
		Media media = session.getPopularMedia().get(rand.nextInt() % 10);
		session.likeMedia(media.getId());
		session.removeMediaLike(media.getId());
	}

	@Test
	public void testGetTag() throws Exception {
		/*
		 * Test that no Exception is thrown for a tag
		 */
		getNewSession().getTag("yolo");
	}

	@Test
	public void testGetRecentMediaForTag() throws Exception {
		InstagramSession session = getNewSession();
		PaginatedCollection<Media> media = session.getRecentMediaForTag("yolo");
		int i = 0;
		for(Media _media: media) {
			System.out.println(_media);
			if(i++ == 50) {
				break;
			}
		}
	}

	@Test
	public void testSearchTags() throws Exception {
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().searchTags("yolo");
	}

	@Test
	public void testGetLocation() throws Exception {
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().getLocation(Constants.VALID_LOCATION_ID);
	}

	@Test
	public void testGetRecentMediaFromLocation() throws Exception {
		InstagramSession session = getNewSession();
		PaginatedCollection<Media> media = session.getRecentMediaFromLocation(Constants.VALID_LOCATION_ID);
		int i = 0;
		for(Media _media: media) {
			System.out.println(_media);
			if(i++ == 50) {
				break;
			}
		}
	}

}
