(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .factory('PromocaoSearch', PromocaoSearch);

    PromocaoSearch.$inject = ['$resource'];

    function PromocaoSearch($resource) {
        var resourceUrl =  'api/_search/promocaos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
