(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('Sub_grupoDetailController', Sub_grupoDetailController);

    Sub_grupoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Sub_grupo'];

    function Sub_grupoDetailController($scope, $rootScope, $stateParams, entity, Sub_grupo) {
        var vm = this;
        vm.sub_grupo = entity;
        
        var unsubscribe = $rootScope.$on('gpwebApp:sub_grupoUpdate', function(event, result) {
            vm.sub_grupo = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
