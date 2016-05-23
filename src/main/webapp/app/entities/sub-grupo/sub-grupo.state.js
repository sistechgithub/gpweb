(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('sub-grupo', {
            parent: 'entity',
            url: '/sub-grupo?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'gpwebApp.sub_grupo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sub-grupo/sub-grupos.html',
                    controller: 'Sub_grupoController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sub_grupo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('sub-grupo-detail', {
            parent: 'entity',
            url: '/sub-grupo/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'gpwebApp.sub_grupo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sub-grupo/sub-grupo-detail.html',
                    controller: 'Sub_grupoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sub_grupo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Sub_grupo', function($stateParams, Sub_grupo) {
                    return Sub_grupo.get({id : $stateParams.id});
                }]
            }
        })
        .state('sub-grupo.new', {
            parent: 'sub-grupo',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sub-grupo/sub-grupo-dialog.html',
                    controller: 'Sub_grupoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nm_sub_grupo: null,
                                vl_custo: null,
                                vl_valor: null,
                                dt_operacao: null,
                                fl_envio: null,
                                nn_novo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('sub-grupo', null, { reload: true });
                }, function() {
                    $state.go('sub-grupo');
                });
            }]
        })
        .state('sub-grupo.edit', {
            parent: 'sub-grupo',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sub-grupo/sub-grupo-dialog.html',
                    controller: 'Sub_grupoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sub_grupo', function(Sub_grupo) {
                            return Sub_grupo.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('sub-grupo', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sub-grupo.delete', {
            parent: 'sub-grupo',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sub-grupo/sub-grupo-delete-dialog.html',
                    controller: 'Sub_grupoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Sub_grupo', function(Sub_grupo) {
                            return Sub_grupo.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('sub-grupo', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
