(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('FilialDetailController', FilialDetailController);

    FilialDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Filial'];

    function FilialDetailController($scope, $rootScope, $stateParams, entity, Filial) {
        var vm = this;
        vm.filial = entity;
        
        var unsubscribe = $rootScope.$on('gpwebApp:filialUpdate', function(event, result) {
            vm.filial = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
