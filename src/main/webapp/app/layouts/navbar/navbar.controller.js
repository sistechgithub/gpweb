(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', '$ocLazyLoad', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function NavbarController ($state, $ocLazyLoad, Auth, Principal, ProfileService, LoginService) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerDisabled = response.swaggerDisabled;
        });

        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home');
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }
        
        $ocLazyLoad.load([{
          files: ['content/js/AdminLTE-navbar.js'],
          cache: false
        },{
          files: ['content/js/fullscreen.js'],
          cache: false
        }]);

    }
})();
