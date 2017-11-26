(
        function (ng) {
            var mod = ng.module("bibliotecaModule");
            mod.constant("bibliotecaContext", "api/999/bibliotecas");
            mod.controller('bibliotecaUpdateCtrl', ['$scope', '$http', 'bibliotecasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, bibliotecaContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idBiblioteca = $state.params.bibliotecaId;
                     $scope.dataNewBiblioteca={};    

                    //Consulto el autor a editar.
                    $http.get(bibliotecaContext + '/' + idBiblioteca).then(function (response) {
                        var biblioteca = response.data;
                        $scope.dataNewBiblioteca.name = biblioteca.name;
                        $scope.dataNewBiblioteca.ubicacion = biblioteca.ubicacion;
                        $scope.dataNewBiblioteca.imagen = biblioteca.imagen;
                    });        
                    

                    $scope.createBiblioteca = function () { 
                    console.info(bibliotecaContext+'/' + idBiblioteca);                  
                        $http.put(bibliotecaContext + "/" + idBiblioteca, $scope.dataNewBiblioteca).then(function (response) {                            
                            $state.go('bibliotecasList', {bibliotecaId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);