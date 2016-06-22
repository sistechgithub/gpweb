(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ClassProdutoDeleteController',ClassProdutoDeleteController);

    ClassProdutoDeleteController.$inject = ['$uibModalInstance', 'entity', 'ClassProduto'];

    function ClassProdutoDeleteController($uibModalInstance, entity, ClassProduto) {
        var vm = this;

        vm.classProduto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ClassProduto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
