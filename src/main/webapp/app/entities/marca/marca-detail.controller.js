(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('MarcaDetailController', MarcaDetailController);

    MarcaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Marca'];

    function MarcaDetailController($scope, $rootScope, $stateParams, entity, Marca) {
        var vm = this;
        vm.marca = entity;
        
        var unsubscribe = $rootScope.$on('gpwebApp:marcaUpdate', function(event, result) {
            vm.marca = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
