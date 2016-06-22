(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('GrupoDialogController', GrupoDialogController);

    GrupoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Grupo'];

    function GrupoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Grupo) {
        var vm = this;
        vm.grupo = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gpwebApp:grupoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };
        
        var onBeforeSaveOrUpdate = function(){       	        	
        	vm.grupo.nmGrupo = angular.uppercase(vm.grupo.nmGrupo); //Setting to uppercase
        };   

        vm.save = function () {
            vm.isSaving = true;
            
            onBeforeSaveOrUpdate(); //validations rules
            
            if (vm.grupo.id !== null) {
                Grupo.update(vm.grupo, onSaveSuccess, onSaveError);
            } else {
                Grupo.save(vm.grupo, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.datePickerOpenStatus = {};
        vm.datePickerOpenStatus.dtPromo = false;
        vm.datePickerOpenStatus.dtOperacao = false;

        vm.openCalendar = function(date) {
            vm.datePickerOpenStatus[date] = true;
        };
    }
})();
