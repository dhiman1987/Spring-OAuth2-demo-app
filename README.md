# Spring OAuth2 demo app
 This app demonstrates the usage of Spring Security and Github OAuth2

## Local setup
To Find the Oauth2 *client ID* and *client secret* that needs to be set as environment variables. Navigate to 
- Login to Github
- Navigate to *setting -> Developer Settings -> OauthApps
  https://github.com/settings/developers
- Create a new app or open an existing app to retrive the values.
- **Do not commit these and keep them safe**

Run as normal Spring Boot application
## Observations

- **/ - root** is the public page that needs to authentication
- **/home** is the secure page that needs login

When you login for the first time or new browser on navigating to */home*.
you are redirected to the Github Oauth page
On successful authentication you are redirected to */home* page.

When you logout. By clicking *logout* link or navigating to */logout*.
You are navigated to */* as expected. But when you click the */home* link. 
You are taken to the Home page WITHOUT redirecting to the Github OAuth2 page.
This may give you the feeling that logout is not working.

However, this is not the case. The Authentication is happening behind the scene.
you can observe the *JSESSIONID* in the browser console (Application -> cookie).
Notice that this gets deleted on logout and a new one is created on navigating to */home* post logout navigation.
You will also notice a very minute lag in navigation, this is due to the authentication that is happening behind the scene.

The **Oauh2 client** session gets killed but somehow the Session in the **Outh2 resource server**, 
Github in this case remains active unless you close the browser.
There should be a way to do this but as of now the above behaviour looks to be ok in terms of UX.

### Another way to test this logout and re-login 
- Log out 
- Turn off Internet (WiFi)
- Try to navigate to */home*. You will get Internet not working page. This is because, the application is trying to reach Github for authentication but failing.
- Turn back Internet
- Now try the home navigation again. This time it works. as it is able to reach Github for authentication.
- Go back to root (/)
- Turn off Internet
- Try to navigate to */home*. This time it would work as the OAuth2 Client session is active.

**So this proves that logout is working as expected. After you logout you are getting logged in via remote Oauth2 mechanism.
The seamless behaviour of the process is giving the illusion that the Logout has not happened and you do not need to re-login**

## Future task
- Customize the Oauth Login pages, if possible
- Logout or kill the Oauth2 Resource server session on logout. (is this necessary? not sure)
