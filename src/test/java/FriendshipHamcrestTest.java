import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class FriendshipHamcrestTest {
    private Friendship friendship;

    @Before
    public void setUp(){
        friendship = new Friendship();
    }
    @Test
    public void classInstance(){
        assertThat(friendship.getClass(), is(Friendship.class));
    }

    @Test
    public void test_empty_friendships() {
        assertThat(friendship.getFriendsList("Ola"), is(nullValue()));
    }

    @Test
    public void test_make_empty_friends() {
        friendship.makeFriends(null,null);
        assertThat(friendship.getFriendsList("Ola"), is(nullValue()));
    }
    @Test
    public void test_make_friends_both_ways() {
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.getFriendsList("Ola"), contains("Ala"));
        assertThat(friendship.getFriendsList("Ala"), contains("Ola"));
    }

    @Test
    public void test_make_more_friends() {
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        friendship.makeFriends("Ola", "Kasia");
        assertThat(friendship.getFriendsList("Ola"), contains("Ala", "Basia", "Kasia"));
    }

    @Test
    public void test_get_list(){
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        assertThat(friendship.getFriendsList("Ola"), hasSize(equalTo(2)));
        assertThat(friendship.getFriendsList("Ala"), hasSize(equalTo(1)));
        assertThat(friendship.getFriendsList("Basia"), hasSize(equalTo(1)));
    }

    @Test
    public void test_my_friends_are_not_yours() {
        friendship.makeFriends("Ola", "Ala");
        friendship.makeFriends("Ola", "Basia");
        assertThat(friendship.getFriendsList("Ala"), contains("Ola"));
        assertThat(friendship.getFriendsList("Ala"), not(contains("Basia")));
        assertThat(friendship.getFriendsList("Basia"), contains("Ola"));
        assertThat(friendship.getFriendsList("Basia"), not(contains("Ala")));
    }

    @Test
    public void test_are_friends(){
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.areFriends("Ola", "Ala"), is(true));
        assertThat(friendship.areFriends("Ala", "Ola"), is(true));
    }

    @Test
    public void test_are_friend_where_is_no_friendship(){
        assertThat(friendship.areFriends("Ola", "Ala"), is(false));
    }


    @Test
    public void test_are_not_friends(){
        friendship.makeFriends("Ola", "Ela");
        friendship.makeFriends("Ola", "Ala");
        assertThat(friendship.areFriends("Ela", "Ala"), is(false));
    }


    @Test
    public void test_big_friendship(){

    }


    @After
    public void tearDown() {
        friendship = null;
    }

}
