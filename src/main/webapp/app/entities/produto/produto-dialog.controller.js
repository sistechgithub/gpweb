(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ProdutoDialogController', ProdutoDialogController);

    ProdutoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'DataUtils', 'entity', 'Produto', 'Grupo', 'Marca', 'Unidade'];

    function ProdutoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, DataUtils, entity, Produto, Grupo, Marca, Unidade) {
        var vm = this;

        vm.produto = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.grupos = Grupo.query({filter: 'produto-is-null'});
        $q.all([vm.produto.$promise, vm.grupos.$promise]).then(function() {
            if (!vm.produto.grupo || !vm.produto.grupo.id) {
                return $q.reject();
            }
            return Grupo.get({id : vm.produto.grupo.id}).$promise;
        }).then(function(grupo) {
            vm.grupos.push(grupo);
        });
        vm.marcas = Marca.query({filter: 'produto-is-null'});
        $q.all([vm.produto.$promise, vm.marcas.$promise]).then(function() {
            if (!vm.produto.marca || !vm.produto.marca.id) {
                return $q.reject();
            }
            return Marca.get({id : vm.produto.marca.id}).$promise;
        }).then(function(marca) {
            vm.marcas.push(marca);
        });
        vm.unidades = Unidade.query({filter: 'produto-is-null'});
        $q.all([vm.produto.$promise, vm.unidades.$promise]).then(function() {
            if (!vm.produto.unidade || !vm.produto.unidade.id) {
                return $q.reject();
            }
            return Unidade.get({id : vm.produto.unidade.id}).$promise;
        }).then(function(unidade) {
            vm.unidades.push(unidade);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.produto.id !== null) {
                Produto.update(vm.produto, onSaveSuccess, onSaveError);
            } else {
                Produto.save(vm.produto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('gpwebApp:produtoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setBlImagem = function ($file, produto) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        produto.blImagem = base64Data;
                        produto.blImagemContentType = $file.type;
                    });
                });
            }
        };

    }
})();
