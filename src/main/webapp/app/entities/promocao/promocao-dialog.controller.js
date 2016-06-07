(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('PromocaoDialogController', PromocaoDialogController);

    PromocaoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Promocao'];

    function PromocaoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Promocao) {
        var vm = this;
        vm.promocao = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gpwebApp:promocaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.promocao.id !== null) {
                Promocao.update(vm.promocao, onSaveSuccess, onSaveError);
            } else {
                Promocao.save(vm.promocao, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.datePickerOpenStatus = {};
        vm.datePickerOpenStatus.dtVencimento = false;

        vm.openCalendar = function(date) {
            vm.datePickerOpenStatus[date] = true;
        };
    }
})();
