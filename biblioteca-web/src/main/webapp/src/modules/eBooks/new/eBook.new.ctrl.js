(function (ng) {
        var mod = ng.module("eBookModule");
        mod.constant("eBooksContext", "api/999/eBooks");
        mod.controller('eBookNewCtrl', ['$scope', '$http', 'eBooksContext', '$state', '$rootScope',
            function ($scope, $http, eBooksContext, $state, $rootScope) {
                $rootScope.edit = false;
                $scope.createEBook = function () {
                    $http.post(eBooksContext, {
                        id : $scope.eBookId,
                        name : $scope.eBookTitle,
                        autor : $scope.eBookAuthor,
                        numeroPaginas : $scope.eBookNumPages
                    }).then(function (response) {
                        //eBook created successfully
                        $state.go('eBooksList', {eBookId: response.data.id}, {reload: true});
                    });
                };
            }
        ]);
    }
)(angular);