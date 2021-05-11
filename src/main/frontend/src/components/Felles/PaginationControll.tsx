import React, { BaseSyntheticEvent, FC } from "react";
import { PageItem, Pagination } from "react-bootstrap";

interface RouteParams {
  side: number;
  antallSider: number;
  setSide: (event: BaseSyntheticEvent) => void;
}

const PaginationControll: FC<RouteParams> = ({
  side,
  antallSider,
  setSide,
}) => {
  let items = [];
  for (let i = 1; i <= antallSider; i++) {
    items.push(
      <PageItem
        onClick={(event) => setSide(event)}
        data-text={i}
        key={i}
        active={i === side}
      >
        {i}
      </PageItem>
    );
  }

  return (
    <Pagination size="sm" className="mb-0 float-right">
      <Pagination.First
        onClick={(event) => setSide(event)}
        key={"f0"}
        data-text={1}
      />
      <Pagination.Prev
        onClick={(event) => setSide(event)}
        key={"l1"}
        data-text={side - 1}
        disabled={side === 1}
      />

      {items}
      <Pagination.Next
        onClick={(event) => setSide(event)}
        key={"f1"}
        data-text={side + 1}
        disabled={side === antallSider}
      />
      <Pagination.Last
        onClick={(event) => setSide(event)}
        key={"l0"}
        data-text={antallSider}
      />
    </Pagination>
  );
};

export default PaginationControll;
