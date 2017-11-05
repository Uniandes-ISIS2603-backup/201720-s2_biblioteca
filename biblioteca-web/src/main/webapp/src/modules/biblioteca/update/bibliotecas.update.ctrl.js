(
        function (ng) {
            var mod = ng.module("bibliotecaModule");
            mod.constant("bibliotecaContext", "api/999/bibliotecas");
            mod.controller('bibliotecaUpdateCtrl', ['$scope', '$http', 'bibliotecasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, bibliotecaContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idBiblioteca = $state.params.bibliotecaId;

                    //Consulto el autor a editar.
                    console.info(bibliotecaContext+'/' + idBiblioteca);
                    $http.get(bibliotecaContext + '/' + idBiblioteca).then(function (response) {
                        var biblioteca = response.data;
                        $scope.bibliotecaName = biblioteca.name;
                        $scope.bibliotecaUbicacion = biblioteca.ubicacion;
                        $scope.bibliotecaImagen = biblioteca.imagen;
                    });        
                    

                    $scope.createBiblioteca = function () {                   
                        $http.put(bibliotecaContext + "/" + idBiblioteca, {
                            name: $scope.bibliotecaName,
                            ubicacion: $scope.bibliotecaUbicacion,
                            imagen: $scope.bibliotebibliotecaImagencaDireccion
                        }).then(function (response) {                            
                            $state.go('bibliotecasList', {bibliotecaId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);