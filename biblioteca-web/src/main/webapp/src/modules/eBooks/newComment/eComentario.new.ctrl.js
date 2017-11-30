(function (ng) {
        var mod = ng.module("eBookModule");
        mod.constant("eBooksContext", "api/999/eBooks");
        mod.controller('eComentarioNewCtrl', ['$scope', '$http', 'eBooksContext', '$state', '$rootScope',
            function ($scope, $http, eBooksContext, $state, $rootScope) {
               $scope.objeto = {};
               $scope.objeto.id = 1;
               var idEBook = $state.params.eBookId;
               $scope.ebookId = idEBook;
                $scope.createEComentario = function () {
                    console.log($scope.objeto);
                    $http.post(eBooksContext + '/' + idEBook+ '/comments', $scope.objeto ).then(function (response) {
                        $state.go('eBookDetail', {eBookId: idEBook}, {reload: true});
                    });
                };
            }
        ]);
    })(angular);