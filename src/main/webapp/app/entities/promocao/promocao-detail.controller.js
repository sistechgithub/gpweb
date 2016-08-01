(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('PromocaoDetailController', PromocaoDetailController);

    PromocaoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Promocao'];

    function PromocaoDetailController($scope, $rootScope, $stateParams, entity, Promocao) {
        var vm = this;

        vm.promocao = entity;

        var unsubscribe = $rootScope.$on('gpwebApp:promocaoUpdate', function(event, result) {
            vm.promocao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
