(function() {
    'use strict';

    angular
        .module('gpwebApp', [
            'ngStorage', 
            'tmh.dynamicLocale',
            'pascalprecht.translate', 
            'ngResource',
            'ngCookies',
            'ngAria',
            'ngCacheBuster',
            'ngFileUpload',
            'ui.bootstrap',
            'ui.bootstrap.datetimepicker',
            'ui.router',
            'infinite-scroll',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar',
            'oc.lazyLoad',
            'whimsicalRipple',
            'ui.utils.masks',
            'idf.br-filters'
        ])
        .run(run);

    run.$inject = ['stateHandler', 'translationHandler', '$ocLazyLoad'];

    function run(stateHandler, translationHandler, $ocLazyLoad) {
        stateHandler.initialize();
        translationHandler.initialize();
        
        $ocLazyLoad.load([{
            files: ['content/js/AdminLTE.js'],
            cache: true
        }]);
    }
})();
