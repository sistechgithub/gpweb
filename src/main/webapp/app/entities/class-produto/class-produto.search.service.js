(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .factory('ClassProdutoSearch', ClassProdutoSearch);

    ClassProdutoSearch.$inject = ['$resource'];

    function ClassProdutoSearch($resource) {
        var resourceUrl =  'api/_search/class-produtos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
