(function() {
    'use strict';
    angular
        .module('gpwebApp')
        .factory('Grupo', Grupo);

    Grupo.$inject = ['$resource', 'DateUtils'];

    function Grupo ($resource, DateUtils) {
        var resourceUrl =  'api/grupos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dtPromo = DateUtils.convertLocalDateFromServer(data.dtPromo);
                    data.dtOperacao = DateUtils.convertLocalDateFromServer(data.dtOperacao);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.dtPromo = DateUtils.convertLocalDateToServer(data.dtPromo);
                    data.dtOperacao = DateUtils.convertLocalDateToServer(data.dtOperacao);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.dtPromo = DateUtils.convertLocalDateToServer(data.dtPromo);
                    data.dtOperacao = DateUtils.convertLocalDateToServer(data.dtOperacao);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
