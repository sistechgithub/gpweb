(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('SubgrupoDetailController', SubgrupoDetailController);

    SubgrupoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Subgrupo'];

    function SubgrupoDetailController($scope, $rootScope, $stateParams, entity, Subgrupo) {
        var vm = this;
        vm.subgrupo = entity;
        
        var unsubscribe = $rootScope.$on('gpwebApp:subgrupoUpdate', function(event, result) {
            vm.subgrupo = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
