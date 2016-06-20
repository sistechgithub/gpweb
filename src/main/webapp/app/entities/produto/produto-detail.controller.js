(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ProdutoDetailController', ProdutoDetailController);

    ProdutoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Produto', 'Grupo', 'Marca', 'Unidade'];

    function ProdutoDetailController($scope, $rootScope, $stateParams, DataUtils, entity, Produto, Grupo, Marca, Unidade) {
        var vm = this;

        vm.produto = entity;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('gpwebApp:produtoUpdate', function(event, result) {
            vm.produto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
