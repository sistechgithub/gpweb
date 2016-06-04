(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('SubgrupoDialogController', SubgrupoDialogController);

    SubgrupoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Subgrupo'];

    function SubgrupoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Subgrupo) {
        var vm = this;
        vm.subgrupo = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gpwebApp:subgrupoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.subgrupo.id !== null) {
                Subgrupo.update(vm.subgrupo, onSaveSuccess, onSaveError);
            } else {
                Subgrupo.save(vm.subgrupo, onSaveSuccess, onSaveError);
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
