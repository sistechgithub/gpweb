'use strict';

describe('Controller Tests', function() {

    describe('ClassProduto Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockClassProduto;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockClassProduto = jasmine.createSpy('MockClassProduto');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ClassProduto': MockClassProduto
            };
            createController = function() {
                $injector.get('$controller')("ClassProdutoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'gpwebApp:classProdutoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
