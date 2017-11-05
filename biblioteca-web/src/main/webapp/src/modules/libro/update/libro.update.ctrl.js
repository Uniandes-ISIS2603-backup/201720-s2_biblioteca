(
        function (ng) {
            var mod = ng.module("librosModule");
            mod.constant("librosContext", "api/999/libros");
            mod.constant("prestamoContext", "api/books");
            mod.controller('librosUpdateCtrl', ['$scope', '$http', 'librosContext', '$state', 'prestamoContext', '$rootScope', '$filter',
                function ($scope, $http, librosContext, $state, prestamoContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idLibro = $state.params.libroId;

                    //Consulto el autor a editar.
                       console.info(librosContext+'/' + idLibro);
                    $http.get(librosContext + '/' + idLibro).then(function (response) {
                        var libro = response.data;
                        $scope.libroName = libro.name;
                        $scope.libroAnioPublicacion = libro.anioPublicacion;
                        $scope.libroNumeroPaginas = libro.numPaginas;
                    });           
                    

                    $scope.createLibro = function () {                   
                        $http.put(librosContext + '/' + idLibro, {
                          name: $scope.libroName,
                   anioPublicacion: $scope.libroAnioPublicacion,
                   numPaginas: $scope.libroNumeroPaginas
                        }).then(function (response) {                            
                            $state.go('librosList', {libroId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);