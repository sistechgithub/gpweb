(function() {
    'use strict';
    angular
        .module('gpwebApp')
        .factory('ClassProduto', ClassProduto);

    ClassProduto.$inject = ['$resource'];

    function ClassProduto ($resource) {
        var resourceUrl =  'api/class-produtos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
