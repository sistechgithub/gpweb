(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('GrupoDetailController', GrupoDetailController);

    GrupoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Grupo'];

    function GrupoDetailController($scope, $rootScope, $stateParams, entity, Grupo) {
        var vm = this;
        vm.grupo = entity;
        
        var unsubscribe = $rootScope.$on('gpwebApp:grupoUpdate', function(event, result) {
            vm.grupo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
