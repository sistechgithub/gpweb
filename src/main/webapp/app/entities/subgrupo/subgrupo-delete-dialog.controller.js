(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('SubgrupoDeleteController',SubgrupoDeleteController);

    SubgrupoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Subgrupo'];

    function SubgrupoDeleteController($uibModalInstance, entity, Subgrupo) {
        var vm = this;
        vm.subgrupo = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Subgrupo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
