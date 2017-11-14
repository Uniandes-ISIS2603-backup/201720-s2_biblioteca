(function (ng) {
        var mod = ng.module("eBookModule");
        mod.constant("eBooksContext", "api/999/eBooks");
        mod.controller('eBookUpdateCtrl', ['$scope', '$http', 'eBooksContext', '$state', '$rootScope', '$filter',
            function ($scope, $http, eBooksContext, $state, booksContext, $rootScope, $filter) {
                $rootScope.edit = true;

                var idEBook = $state.params.eBookId;

                //Consulto el eBook a editar.
                $http.get(eBooksContext + '/' + idEBook).then(function (response) {
                    var eBook = response.data;
                    $scope.eBookName = eBook.name;
                    $scope.eBookAutor = eBook.autor;
                    $scope.eBookNumeroPaginas = eBook.numeroPaginas;
                });

                $scope.createEBook = function () {
                    $http.put(eBooksContext + "/" + idEBook, {
                        name: $scope.eBookName,
                        autor: $scope.eBookAutor,
                        numeroPaginas: $scope.eBookNumeroPaginas
                    }).then(function (response) {
                        //eBook created successfully
                        $state.go('eBooksList', {eBookId: response.data.id}, {reload: true});
                    });
                };


            }
        ]);
    })(angular);