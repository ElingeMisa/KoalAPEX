mvn verify
docker stop agileOrganizerContainer
docker rm -f agileOrganizerContainer
docker rmi agileOrganizerContainer
call mvn verify
docker build -f Dockerfile --platform linux/amd64 -t mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:0.6 .
#docker push mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:0.1
docker run -it --name agileOrganizerContainer -p 8080:8080 mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:0.6
# ax3ubzsr6auc
# docker login -u ax3ubzsr6auc/a00835693@tec.mx mx-queretaro-1.ocir.io 
# D5E>6F8n)+n1:ZnW3{t;  
# docker build -f Dockerfile --platform linux/amd64 -t mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:0.1 .
# docker build -f Dockerfile --platform linux/386 -t mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:1.1 .
# docker run -it --name agileOrganizerContainer -p 8080:8080 mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:0.1
# docker push mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:0.1
# docker run -it --name agileOrganizerContainer -p 8080:8080 mx-queretaro-1.ocir.io/ax3ubzsr6auc/reacttodo/7kex2/todolistapp-springboot:0.5