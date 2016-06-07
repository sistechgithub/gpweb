(function() {
    'use strict';
    angular
        .module('gpwebApp')
        .factory('Marca', Marca);

    Marca.$inject = ['$resource', 'DateUtils'];

    function Marca ($resource, DateUtils) {
        var resourceUrl =  'api/marcas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dtOperacao = DateUtils.convertLocalDateFromServer(data.dtOperacao);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.dtOperacao = DateUtils.convertLocalDateToServer(data.dtOperacao);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.dtOperacao = DateUtils.convertLocalDateToServer(data.dtOperacao);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
