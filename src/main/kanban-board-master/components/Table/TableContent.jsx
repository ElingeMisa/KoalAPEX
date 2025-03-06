import React from 'react';
import NewItem from './NewItem';
import { Button, CircularProgress, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import dayjs from 'dayjs';
import './TableContent.css';

function TableContent({ items, toggleDone, deleteItem, isLoading, error, isInserting, addItem }) {
  return (
    <div>
      <NewItem addItem={addItem} isInserting={isInserting} />
      {error &&
        <p className="ErrorMessage">Error: {error.message}</p>
      }
      {isLoading &&
        <CircularProgress className="CircularProgress" />
      }
      {!isLoading &&
        <TableContainer component={Paper} className="TableContainer">
          <Table className="Table">
            <TableHead className="TableHead">
              <TableRow>
                <TableCell className="TableCell">Description</TableCell>
                <TableCell className="TableCell">Date</TableCell>
                <TableCell className="TableCell">Action</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {items.map(item => (
                !item.done && (
                  <TableRow key={item.id} className="TableRow">
                    <TableCell className="TableCell">{item.description}</TableCell>
                    <TableCell className="TableCell">{dayjs(item.createdAt).format('MMM D hh:mm:ss')}</TableCell>
                    <TableCell className="TableCell">
                      <Button variant="contained" className="DoneButton" onClick={(event) => toggleDone(event, item.id, item.description, !item.done)} size="small">
                        Done
                      </Button>
                    </TableCell>
                  </TableRow>
                )))}
            </TableBody>
          </Table>
          <h3 className="donelist">
            Done items
          </h3>
          <Table className="Table">
            <TableHead className="TableHead">
              <TableRow>
                <TableCell>Description</TableCell>
                <TableCell>Date</TableCell>
                <TableCell>Action</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {items.map(item => (
                item.done && (
                  <TableRow key={item.id} className="TableRow">
                    <TableCell className="TableCell">{item.description}</TableCell>
                    <TableCell className="TableCell">{dayjs(item.createdAt).format('MMM D hh:mm:ss')}</TableCell>
                    <TableCell className="TableCell">
                      <Button variant="contained" className="DoneButton" onClick={(event) => toggleDone(event, item.id, item.description, !item.done)} size="small">
                        Undo
                      </Button>
                      <Button startIcon={<DeleteIcon />} variant="contained" className="DeleteButton" onClick={() => deleteItem(item.id)} size="small">
                        Delete
                      </Button>
                    </TableCell>
                  </TableRow>
                )))}
            </TableBody>
          </Table>
        </TableContainer>
      }
    </div>
  );
}

export default TableContent;