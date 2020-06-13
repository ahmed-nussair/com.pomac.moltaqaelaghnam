package com.pomac.moltaqaelaghnam.view.interfaces;

public interface AppNavigator {

    void navigateToMainPage();

    void navigateToWishListPage();

    void navigateToNotificationsPage();

    void navigateToMessagesPage();

    void navigateToUserAccountPage();

    void navigateToInfoPage();

    void navigateToContactUsPage();

    void navigateToMorePage();

    void navigateToAddingAdPage();

    void navigateToAdDetailsPage(int adId, String adTitle, String source);
}
