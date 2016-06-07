(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('PromocaoDeleteController',PromocaoDeleteController);

    PromocaoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Promocao'];

    function PromocaoDeleteController($uibModalInstance, entity, Promocao) {
        var vm = this;
        vm.promocao = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Promocao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
