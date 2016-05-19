(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('SidebarController', SidebarController);

    SidebarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function SidebarController ($state, Auth, Principal, ProfileService, LoginService) {
        var vm = this;

        vm.isAuthenticated = Principal.isAuthenticated;

         ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerDisabled = response.swaggerDisabled;
        });

        vm.$state = $state;

        function logout() {
            Auth.logout();
            $state.go('home');
        }
    }
})();