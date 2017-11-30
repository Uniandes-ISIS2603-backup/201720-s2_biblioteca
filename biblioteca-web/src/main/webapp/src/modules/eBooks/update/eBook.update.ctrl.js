(function (ng) {
        var mod = ng.module("eBookModule");
        mod.constant("eBooksContext", "api/999/eBooks");
        mod.controller('eBookUpdateCtrl', ['$scope', '$http', 'eBooksContext', '$state', '$rootScope', '$filter',
            function ($scope, $http, eBooksContext, $state, booksContext, $rootScope) {
                $rootScope.edit = true;

                var idEBook = $state.params.eBookId;
                $scope.objeto = {};

                //Consulto el eBook a editar.
                $http.get(eBooksContext + '/' + idEBook).then(function (response) {
                    var eBook = response.data;
                    $scope.objeto.name = eBook.name;
                    $scope.objeto.autor = eBook.autor;
                    $scope.objeto.numeroPaginas = eBook.numeroPaginas;
                    $scope.objeto.imagen = eBook.imagen;
                    $scope.objeto.direccion = eBook.direccion;
                });

                $scope.createEBook = function () {
                    $http.put(eBooksContext + "/" + idEBook,$scope.objeto).then(function (response) {
                        //eBook created successfully
                        $state.go('eBooksList', {eBookId: response.data.id}, {reload: true});
                    });
                };


            }
        ]);
    })(angular);