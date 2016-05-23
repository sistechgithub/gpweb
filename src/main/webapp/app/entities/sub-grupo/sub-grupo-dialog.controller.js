(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('Sub_grupoDialogController', Sub_grupoDialogController);

    Sub_grupoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Sub_grupo'];

    function Sub_grupoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Sub_grupo) {
        var vm = this;
        vm.sub_grupo = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gpwebApp:sub_grupoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.sub_grupo.id !== null) {
                Sub_grupo.update(vm.sub_grupo, onSaveSuccess, onSaveError);
            } else {
                Sub_grupo.save(vm.sub_grupo, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.datePickerOpenStatus = {};
        vm.datePickerOpenStatus.dt_operacao = false;

        vm.openCalendar = function(date) {
            vm.datePickerOpenStatus[date] = true;
        };
    }
})();
