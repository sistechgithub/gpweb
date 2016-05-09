(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('GrupoDeleteController',GrupoDeleteController);

    GrupoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Grupo'];

    function GrupoDeleteController($uibModalInstance, entity, Grupo) {
        var vm = this;
        vm.grupo = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Grupo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
