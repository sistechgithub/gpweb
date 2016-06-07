(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('MarcaDeleteController',MarcaDeleteController);

    MarcaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Marca'];

    function MarcaDeleteController($uibModalInstance, entity, Marca) {
        var vm = this;
        vm.marca = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Marca.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
