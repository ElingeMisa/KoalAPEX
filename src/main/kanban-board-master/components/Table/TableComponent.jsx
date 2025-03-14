/*
## MyToDoReact version 1.0.
##
## Copyright (c) 2022 Oracle, Inc.
## Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
*/
/*
 * This is the application main React component. We're using "function"
 * components in this application. No "class" components should be used for
 * consistency.
 * @author  jean.de.lavarene@oracle.com
 */
import React, { useState, useEffect } from 'react';
import API from '../API/API';
import TableContent from './TableContent';

/* In this application we're using Function Components with the State Hooks
 * to manage the states. See the doc: https://reactjs.org/docs/hooks-state.html
 * This App component represents the entire app. It renders a NewItem component
 * and two tables: one that lists the todo items that are to be done and another
 * one with the items that are already done.
 */
function TableComponent() {
    // isLoading is true while waiting for the backend to return the list
    // of items. We use this state to display a spinning circle:
    const [isLoading, setLoading] = useState(false);
    // Similar to isLoading, isInserting is true while waiting for the backend
    // to insert a new item:
    const [isInserting, setInserting] = useState(false);
    // The list of todo items is stored in this state. It includes the "done"
    // "not-done" items:
    const [items, setItems] = useState([]);
    // In case of an error during the API call:
    const [error, setError] = useState();

    // Manages the theme of the table
    const [theme, setTheme] = useState('light');

    const API_LIST = API.TAREAS;

    useEffect(() => {
        document.body.getAttribute('data-theme') === 'dark' ? setTheme('dark') : setTheme('light');
    }, [theme]);

    // Enables testing mode for when there is no API connection
    const testing_mode = false;
    const test_items = [
      {
        "id": 1,
        "name": "Jane Doe",
        "category": "Epic",
        "dueDate": "2022-01-01 14:33:12",
        "description": "Crear, por medio de las tecnologías puestas a nuestra disposición en a clase de planeación de software, un prototipo que presente las características mínimas viables (definidas en el repositorio base dado por el socio formador) y un servicio adicional que nos diferencie del resto de los equipos.",
        "estimatedHours": "5",
        "state": "Active",
        "done": true
      },
      {
        "id": 2,
        "name": "John Doe",
        "category": "Feature",
        "dueDate": "2022-01-02 15:34:13",
        "description": "Chambea más que no le sabes",
        "estimatedHours": "6",
        "state": "Active",
        "done": false
      },
      {
        "id": 3,
        "name": "Jane Smith",
        "category": "Feature",
        "dueDate": "2022-01-03 16:35:14",
        "description": "Test item 3",
        "estimatedHours": 7,
        "state": "Active",
        "done": true
      },
      {
        "id": 4,
        "name": "John Doe",
        "category": "User Story",
        "dueDate": "2022-01-04 17:36:15",
        "description": "Test item 4",
        "estimatedHours": 8,
        "state": "Active",
        "done": false
      }
    ];

    function deleteItem(deleteId) {
      // console.log("deleteItem("+deleteId+")")
      fetch(API_LIST+"/"+deleteId, {
        method: 'DELETE',
      })
      .then(response => {
        // console.log("response=");
        // console.log(response);
        if (response.ok) {
          // console.log("deleteItem FETCH call is ok");
          return response;
        } else {
          throw new Error('Something went wrong ...');
        }
      })
      .then(
        (result) => {
          const remainingItems = items.filter(item => item.id !== deleteId);
          setItems(remainingItems);
        },
        (error) => {
          setError(error);
        }
      );
    }
    function toggleDone(event, id, description, done) {
      event.preventDefault();
      modifyItem(id, description, done).then(
        (result) => { reloadOneIteam(id); },
        (error) => { setError(error); }
      );
    }
    function reloadOneIteam(id){
      fetch(API_LIST+"/"+id)
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Something went wrong ...');
          }
        })
        .then(
          (result) => {
            const items2 = items.map(
              x => (x.id === id ? {
                 ...x,
                 'description':result.description,
                 'done': result.done
                } : x));
            setItems(items2);
          },
          (error) => {
            setError(error);
          });
    }
    function modifyItem(id, description, done) {
      // console.log("deleteItem("+deleteId+")")
      var data = {"description": description, "done": done};
      return fetch(API_LIST+"/"+id, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
      .then(response => {
        // console.log("response=");
        // console.log(response);
        if (response.ok) {
          // console.log("deleteItem FETCH call is ok");
          return response;
        } else {
          throw new Error('Something went wrong ...');
        }
      });
    }
    /*
    To simulate slow network, call sleep before making API calls.
    const sleep = (milliseconds) => {
      return new Promise(resolve => setTimeout(resolve, milliseconds))
    }
    */
    useEffect(() => {
      setLoading(true);
      // sleep(5000).then(() => {
      fetch(API_LIST)
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Something went wrong ...');
          }
        })
        .then(
          (result) => {
            setLoading(false);
            setItems(result);
            console.log(result);
          },
          (error) => {
            setLoading(false);
            setError(error);
          });

      //})
    },
    // https://en.reactjs.org/docs/faq-ajax.html
    [] // empty deps array [] means
       // this useEffect will run once
       // similar to componentDidMount()
    );
    function addItem(text){
      console.log("addItem("+text+")")
      setInserting(true);
      var data = {};
      console.log(data);
      data.description = text;
      fetch(API_LIST, {
        method: 'POST',
        // We convert the React state to JSON and send it as the POST body
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
      }).then((response) => {
        // This API doens't return a JSON document
        console.log(response);
        console.log();
        console.log(response.headers.location);
        // return response.json();
        if (response.ok) {
          return response;
        } else {
          throw new Error('Something went wrong ...');
        }
      }).then(
        (result) => {
          var id = result.headers.get('location');
          var newItem = {"id": id, "description": text}
          setItems([newItem, ...items]);
          setInserting(false);
        },
        (error) => {
          setInserting(false);
          setError(error);
        }
      );
    }
    return (
      <div className="App">
        {testing_mode && <h4>Testing Mode is Active</h4>}
        <TableContent
          items={testing_mode ? test_items : items}
          toggleDone={toggleDone}
          deleteItem={deleteItem}
          isLoading={isLoading}
          error={testing_mode ? null : error}
          isInserting={isInserting}
          addItem={addItem}
        />
      </div>
    );
}
export default TableComponent;