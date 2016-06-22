(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ClassProdutoDetailController', ClassProdutoDetailController);

    ClassProdutoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'ClassProduto'];

    function ClassProdutoDetailController($scope, $rootScope, $stateParams, entity, ClassProduto) {
        var vm = this;

        vm.classProduto = entity;

        var unsubscribe = $rootScope.$on('gpwebApp:classProdutoUpdate', function(event, result) {
            vm.classProduto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
