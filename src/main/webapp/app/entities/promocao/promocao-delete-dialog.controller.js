(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('PromocaoDeleteController',PromocaoDeleteController);

    PromocaoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Promocao'];

    function PromocaoDeleteController($uibModalInstance, entity, Promocao) {
        var vm = this;

        vm.promocao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Promocao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
