(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('Sub_grupoDeleteController',Sub_grupoDeleteController);

    Sub_grupoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Sub_grupo'];

    function Sub_grupoDeleteController($uibModalInstance, entity, Sub_grupo) {
        var vm = this;
        vm.sub_grupo = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Sub_grupo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
