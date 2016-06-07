(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('FilialDialogController', FilialDialogController);

    FilialDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Filial'];

    function FilialDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Filial) {
        var vm = this;
        vm.filial = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gpwebApp:filialUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.filial.id !== null) {
                Filial.update(vm.filial, onSaveSuccess, onSaveError);
            } else {
                Filial.save(vm.filial, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.datePickerOpenStatus = {};
        vm.datePickerOpenStatus.dtOperacao = false;

        vm.openCalendar = function(date) {
            vm.datePickerOpenStatus[date] = true;
        };
    }
})();
