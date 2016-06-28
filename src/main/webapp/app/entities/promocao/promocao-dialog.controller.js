(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('PromocaoDialogController', PromocaoDialogController);

    PromocaoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Promocao'];

    function PromocaoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Promocao) {
        var vm = this;

        vm.promocao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.promocao.id !== null) {
                Promocao.update(vm.promocao, onSaveSuccess, onSaveError);
            } else {
                Promocao.save(vm.promocao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('gpwebApp:promocaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dtVencimento = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
