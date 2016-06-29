(function() {
    'use strict';
    angular
        .module('gpwebApp')
        .factory('Promocao', Promocao);

    Promocao.$inject = ['$resource', 'DateUtils'];

    function Promocao ($resource, DateUtils) {
        var resourceUrl =  'api/promocaos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dtVencimento = DateUtils.convertLocalDateFromServer(data.dtVencimento);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.dtVencimento = DateUtils.convertLocalDateToServer(data.dtVencimento);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.dtVencimento = DateUtils.convertLocalDateToServer(data.dtVencimento);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
