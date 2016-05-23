(function() {
    'use strict';
    angular
        .module('gpwebApp')
        .factory('Sub_grupo', Sub_grupo);

    Sub_grupo.$inject = ['$resource', 'DateUtils'];

    function Sub_grupo ($resource, DateUtils) {
        var resourceUrl =  'api/sub-grupos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dt_operacao = DateUtils.convertLocalDateFromServer(data.dt_operacao);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.dt_operacao = DateUtils.convertLocalDateToServer(data.dt_operacao);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.dt_operacao = DateUtils.convertLocalDateToServer(data.dt_operacao);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
