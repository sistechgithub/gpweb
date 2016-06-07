(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('FilialDeleteController',FilialDeleteController);

    FilialDeleteController.$inject = ['$uibModalInstance', 'entity', 'Filial'];

    function FilialDeleteController($uibModalInstance, entity, Filial) {
        var vm = this;
        vm.filial = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Filial.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
