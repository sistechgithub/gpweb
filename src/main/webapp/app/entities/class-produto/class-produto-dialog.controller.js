(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ClassProdutoDialogController', ClassProdutoDialogController);

    ClassProdutoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ClassProduto'];

    function ClassProdutoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ClassProduto) {
        var vm = this;

        vm.classProduto = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.classProduto.id !== null) {
                ClassProduto.update(vm.classProduto, onSaveSuccess, onSaveError);
            } else {
                ClassProduto.save(vm.classProduto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('gpwebApp:classProdutoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
