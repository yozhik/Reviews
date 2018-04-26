Link to the issue: https://www.youtube.com/watch?v=n32N9Z4S3SA&feature=youtu.be  (Collapsing AppBarLayout not scolling).

CollapsingActivity - activity with Collapsing AppBarLayout. Which loads one or two fragments into "fragment_content_holder" and it has TabLayout 
to switch between fragments in view pager.

In activity method onCreate() - I'm just simulating request to server (loadData), and when some fake data is loaded - I am showing fragments in view pager,
on first call - I am creating new TabMenuAdapter extends FragmentPagerAdapter, populate it with fragments and save links to instances. On the next call - 
I don't create fragments from scratch and just populate them with fresh data. 

MenuFragment1, MenuFragment1 - two fragments.
MenuFragment1 - has method public void setupData(SomeCustomData data), to set new data, not recreating fragment on network reconnect.

NetworkStateReceiver - listens to network change and send notifications.

TabMenuAdapter - just simple class to hold fragments.
